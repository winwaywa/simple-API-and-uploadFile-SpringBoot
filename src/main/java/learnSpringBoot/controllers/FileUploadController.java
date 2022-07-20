package learnSpringBoot.controllers;

import learnSpringBoot.model.ResponseObject;
import learnSpringBoot.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/v1/upload")
public class FileUploadController{
    // gọi từ service
    @Autowired
    private IStorageService storageService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file){
        try{
        // lưu file vào folder trên server
        String genneratedFile = storageService.storeFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","upload file successfully",genneratedFile)
        );
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed","upload file failed","")
            );
        }
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName){
        try{
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes); //return ảnh
        }catch(Exception exception){
            return ResponseEntity.noContent().build();
        }
    }

    //How to load all uploaded files ?
    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadedFiles() {
        try {
            List<String> urls = storageService.loadAll()
                    .map(path -> {
                        //convert fileName to url(send request "readDetailFile")
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("ok", "List files successfully", urls));
        }catch (Exception exception) {
            return ResponseEntity.ok(new
                    ResponseObject("failed", "List files failed", new String[] {}));
        }
    }
}