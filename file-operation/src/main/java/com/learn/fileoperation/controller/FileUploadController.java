package com.learn.core.controller;


import com.learn.core.util.FileUtil;
import com.learn.core.util.ZipUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * Created by 一伞烟雨 on 2017/8/8.
 */
@Controller
public class FileUploadController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));


    /**
     * 上传文件或者图片到目录下，本方法还没有完成，文件目录需要上传到数据库
     * @param school
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ModelAndView upload(ModelAndView mv,
                               @RequestParam("school") String school,
                               @RequestParam("name") String name,
                               @RequestParam("homework") String homework,
                               @RequestParam("file") MultipartFile file) throws IOException {
        mv.setViewName("redirect:"+"/");
        if (!file.isEmpty()){
            //文件路径
            File directory = new File("");
            String courseFile = directory.getCanonicalPath()+"/src/main/resources/images/"+homework+"/";

            //构建文件名
            String true_fileName = file.getOriginalFilename();
            String prefix = true_fileName.substring(true_fileName.lastIndexOf(".")+1);
            String fileName = school +"-" + name +"-"+ homework+"." +prefix;

            //判断文件是否存在，不存在则保存，存在就返回是否覆盖
            File file1=new File(courseFile+fileName);
            /*if(file1.exists()){
                System.out.println("文件已存在");
                return mv.addObject("result","failed");
            }*/
            try {
                FileUtil.uploadFile(file.getBytes(), courseFile, fileName);
            } catch (Exception e) {
                logger.info(fileName+"文件保存失败");

                return mv.addObject("result","failed");
            }
            System.out.println("保存成功");
            return mv.addObject("result","success");
        }else{
            return mv.addObject("result","error");
        }
    }

    /**
     * 下载文件或者图片到前端
     * 传入Webapp/imgupload目录下图片的名字，就可以下载了
     * @return
     */

    @RequestMapping("/download")
    public HttpServletResponse download (ModelAndView mv,
                                         String id,
                                         HttpServletResponse response) throws IOException {

        //文件路径
        File directory = new File("");
        String path = directory.getCanonicalPath()+"/src/main/resources/images/"+id;
        File path1 = new File(path);

        File file1 = new File(path);
        if (!file1 .exists()  && !file1 .isDirectory()){
            mv.setViewName("show");
            return (HttpServletResponse) mv.addObject("result","empty");
        }
        //把文件打包
        List<File> fileList = new ArrayList<>();
        for(File temp:path1.listFiles()){
            if(temp.isFile()){
                String temp1 = temp.toString();
                System.out.println("压缩路径"+temp1);
                fileList.add(new File(temp1));
            }
        }
        FileOutputStream true_zipfile = new FileOutputStream(new File(path+".zip"));
        ZipUtils.toZip(fileList, true_zipfile);


        //开始下载文件
        path = path+".zip";
        File file = new File(path);
        // 取得文件名。
        String filename1 = file.getName();
        //下载显示的文件名，解决中文名称乱码
        String filename = new String (filename1.getBytes("UTF-8"),"iso-8859-1");
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        return response;
    }
}
