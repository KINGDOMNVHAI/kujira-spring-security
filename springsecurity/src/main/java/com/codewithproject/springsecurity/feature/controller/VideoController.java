package com.codewithproject.springsecurity.feature.controller;

import com.codewithproject.springsecurity.dto.entitydto.VideoDto;
import com.codewithproject.springsecurity.feature.service.impl.VideoServiceImpl;
import com.codewithproject.springsecurity.util.JSonUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class VideoController {

    @Autowired
    private VideoServiceImpl videoServiceImpl;

    @GetMapping("/public/video/{idVideo}")
    public VideoDto getVideoByID(@PathVariable String idVideo) {
        VideoDto video = new VideoDto();
        try {
            video = videoServiceImpl.getVideo(idVideo);
        } catch (Exception e) {

        }
        return video;
    }

    @PostMapping("/public/video/insert")
    public VideoDto insertVideo(@RequestBody Map<String, Object> request) {
        VideoDto video = new VideoDto();
        Gson g = new Gson();
        try {
            Object videoObj = request.get("video");
            if (videoObj != null) {
                String json = JSonUtil.toJSonString(videoObj);
                video = g.fromJson(json, VideoDto.class);
            }
            return videoServiceImpl.insertVideo(video);
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return video;
    }
}
