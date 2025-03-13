package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.DAO.commentMapper;
import com.ricostone.communitymodule.DAO.postDetailMapper;
import com.ricostone.communitymodule.Entity.Comment;
import com.ricostone.communitymodule.Entity.Post;
import com.ricostone.communitymodule.Entity.User;
import com.ricostone.communitymodule.Service.Impl.userImpl;
import com.ricostone.communitymodule.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class commentController {
    @Autowired
    private userImpl userImpl;
    @Autowired
    private postDetailMapper postDetailMapper;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private commentMapper commentMapper;
    @RequestMapping(path = "/insertComment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<? extends Object> insertComment(String content, int postId, int entityType, int targetId) {
        User user = userImpl.selectUserById(hostHolder.getUser().getUserId());
        if(user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        Comment comment = new Comment();
        comment.setCommentUserId(user.getUserId());
        comment.setCommentContent(content);
        comment.setCommentImageUrl(user.getUserImageUrl());
        comment.setCommentCreateTime(new Date());
        comment.setCommentId(postId);
        comment.setCommentEntityType(entityType);
        Post post = postDetailMapper.selectPostById(postId);
        if(post == null) {
            return ResponseEntity.badRequest().body("Post not found");
        }
        comment.setCommentUsername(user.getUserName());
        Comment targetComment = commentMapper.getCommentById(targetId);
        if(entityType == 1){
            comment.setCommentTargetName(null);
            comment.setCommentTargetId(0);
        }
        else{
            comment.setCommentTargetName(targetComment.getCommentUsername());
            comment.setCommentTargetId(targetComment.getCommentId());
        }
        comment.setCommunityId(1);
        comment.setCommentPostId(postId);
        commentMapper.insertComment(comment);
        return ResponseEntity.ok(comment);
    }
}
