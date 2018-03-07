package com.lmandy.controller;

import com.alibaba.fastjson.JSON;
import com.lmandy.bean.Category;
import com.lmandy.bean.Image;
import com.lmandy.bean.Joke;
import com.lmandy.bean.User;
import com.lmandy.service.ICategoryService;
import com.lmandy.service.IImageService;
import com.lmandy.service.IJokeService;
import com.lmandy.utils.PageBean;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lmandy on 2017/10/17.
 * 内容管理
 */
@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private IJokeService jokeService;
    @Autowired
    private IImageService imageService;
    @Autowired
    private ICategoryService categoryService;

    @Value("${filePath}")
    private String filePath;

    /**
     * 笑话
     * @return
     */
    @RequestMapping("jokeIndex")
    public String jokeIndex(Integer pageNo,String title,String content, Model model){

        Map<String,Object> conMap = new HashMap();

        conMap.put("title",title);
        conMap.put("content",content);

        PageBean<Joke> pageBean = new PageBean(pageNo,10,conMap);

        jokeService.indexPageInfo(pageBean);

        pageBean.setUrl("/content/jokeIndex");
        model.addAttribute("page",pageBean);

        return "joke";
    }

    /**
     * 笑话（添加、修改）
     * @param joke
     * @return
     */
    @RequestMapping("jokeAddOrUpd")
    public String jokeAddOrUpd(Joke joke, HttpServletRequest request){
        if(joke.getId() == null){

            User user  = (User) request.getSession().getAttribute("user");

            if(user == null)
                joke.setUserId(1);
            else
                joke.setUserId(user.getId());

            jokeService.insert(joke);
        }else {
            jokeService.update(joke);
        }

        return "redirect:jokeIndex";
    }

    @RequestMapping("getJokeById/{id}")
    @ResponseBody
    public Joke getJokeById(@PathVariable Integer id){
        Joke joke = jokeService.getById(id);

        return joke;
    }

    /**
     * 笑话删除
     * @param id
     * @return
     */
    @RequestMapping("delJoke")
    public String delJoke(Integer id){

        jokeService.delete(id);

        return "redirect:jokeIndex";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 图片
     */
    @RequestMapping("imgIndex")
    public String imgIndex(Integer pageNo,String categoryId,String fileName,Model model) throws IOException {

        Map<String,Object> conMap = new HashedMap();
        conMap.put("categoryId",categoryId);
        conMap.put("fileName",fileName);

        PageBean<Image> pageBean = new PageBean(pageNo,10,conMap);

        imageService.indexPageInfo(pageBean);



        if(pageBean.getTotalResults() !=null && pageBean.getTotalResults().size() >0){

            for (Image image : pageBean.getTotalResults()) {
                InputStream inputStream = new URL(image.getUrl()).openStream();
                BufferedImage bufferedImage = ImageIO.read(inputStream);

                int height = bufferedImage.getHeight();
                int width = bufferedImage.getWidth();

                System.out.println("图片的高度："+(height*180)/width);

                image.setHeight(String.valueOf((height*180)/width));

                inputStream.close();
            }
        }


        pageBean.setUrl("/content/imgIndex");
        model.addAttribute("page",pageBean);

        List<Category> categories = getCategoryAll();
        model.addAttribute("categories",categories);

        return "img";
    }

    /**
     * 图片（添加、修改）
     * @return
     */
    @RequestMapping("imgAddOrUpd")
    public String imgAddOrUpd(Image image, MultipartFile ImgFile) throws IOException {

        if(image.getId() == null){
            if(ImgFile !=null && StringUtils.isNotBlank(ImgFile.getOriginalFilename())){
                File basePath = new File(filePath);
                if(basePath == null){
                    basePath.mkdirs();
                }

                String extension = FilenameUtils.getExtension(ImgFile.getOriginalFilename());

                String fielName = UUID.randomUUID().toString().replace("-","")+"."+extension;

                ImgFile.transferTo(new File(basePath, fielName));

                image.setUrl(fielName);
            }

            imageService.insert(image);
            //图片，分类关系
            categoryService.fileCategoryRelation(image.getId(),image.getCategoryId());

        }else {
            imageService.update(image);
        }

        return "redirect:imgIndex";
    }

    /**
     * 获取图片信息
     * @param id
     * @return
     */
    @RequestMapping("getImgById/{id}")
    @ResponseBody
    public Image getImgById(@PathVariable Integer id){

        Image image = imageService.getById(id);

        return image;

    }

    /**
     * 图片删除
     * @param id
     * @return
     */
    @RequestMapping("delImg")
    public String delImg(Integer id){

        Image image = getImgById(id);
        if(StringUtils.isNotBlank(image.getUrl())){
            FileUtils.deleteQuietly(new File(image.getUrl()));
        }

        imageService.delete(id);


        return "redirect:imgIndex";
    }

    //////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取所有图片分类信息
     * @return
     */
    public List<Category> getCategoryAll(){
        List<Category> categories = categoryService.getAll();
        return categories;
    }
    @RequestMapping("categoryAddOrUpd")
    @ResponseBody
    public void categoryAddOrUpd(Category category){
        if(category.getId() == null){
            categoryService.insert(category);
        }else {
            categoryService.update(category);
        }
    }

}
