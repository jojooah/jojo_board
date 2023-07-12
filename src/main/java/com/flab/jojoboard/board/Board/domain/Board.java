package com.flab.jojoboard.board.Board.domain;

import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.common.domain.Common;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Alias(value = "Board")
public class Board extends Common {
    private String id;
    private String boardTypeId;
    private String boardName;
    private BoardType boardType;
    private List<Post> PostList;

}
