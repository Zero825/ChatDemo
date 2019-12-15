package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
public class FileController {

    @ResponseBody
    @RequestMapping(value="/imagesupload",method = RequestMethod.POST)
    private void saveImage(@RequestParam("file")MultipartFile srcFile) throws IOException {
        String path="C:\\file\\userimages\\";
        String fileName = srcFile.getOriginalFilename();
        System.out.println(fileName);
        File file = new File(path+fileName);
        file.createNewFile();
        srcFile.transferTo(file);
    }

    @ResponseBody
    @RequestMapping(value="/fileupload",method = RequestMethod.POST)
    private void saveFile(@RequestParam("file")MultipartFile srcFile) throws IOException {
        String path="C:\\file\\userfiles\\";
        String fileName = srcFile.getOriginalFilename();
        System.out.println(fileName);
        File file = new File(path+fileName);
        file.createNewFile();
        srcFile.transferTo(file);
    }
}
