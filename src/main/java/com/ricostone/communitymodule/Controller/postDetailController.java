package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.DAO.commentMapper;
import com.ricostone.communitymodule.Entity.Comment;
import com.ricostone.communitymodule.Entity.Post;
import com.ricostone.communitymodule.Service.Impl.postDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.ricostone.communitymodule.Util.CommunityNormalValue.ENTITY_TYPE_COMMENT;
import static com.ricostone.communitymodule.Util.CommunityNormalValue.ENTITY_TYPE_POST;

/**
 * @author RicoStone
 * @date 2023/12/11
 */
@Controller
public class postDetailController {
   @Autowired
   private postDetailImpl postDetailImpl;
   @Autowired
   private commentMapper commentMapper;

   @RequestMapping(path = "/postDetail/{postId}", method = RequestMethod.GET)
   public String postDetail(@PathVariable("postId") int postId, Model model) {
	  Post post = postDetailImpl.getPostDetail(postId);
	  // 获取所有评论的数量
	  int commentCount = commentMapper.getCommentCount(postId);
	  // 获取评论列表, ENTITY_TYPE_POST 代表评论的实体是帖子
	  List<Comment> commentList = commentMapper.getCommentList(ENTITY_TYPE_POST, postId, null,0);
	  if (commentList != null) {
		 for(Comment comment : commentList){
			List<Comment> replyComment = commentMapper.getCommentList(ENTITY_TYPE_COMMENT,
					postId,comment.getCommentUsername(), comment.getCommentId());
			if(!replyComment.isEmpty()){
			   comment.setChildComment(replyComment);
			}
		 }
	  }
	  model.addAttribute("post", post);
	  model.addAttribute("commentCount", commentCount);
	  model.addAttribute("commentList", commentList);
	  return "/postDetail";
   }
}
