package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController { //ajax를 위한 컨트롤러 따로 만듬
    private final BoardRepository boardRepository;

    @GetMapping(value = "/api/boards") //json으로 보는 주소
    public ApiUtil<?> findAll(HttpServletResponse response){ //?를 넣으면 알아서 맞춰줌
        //response.setStatus(401);
        List<Board> boardList = boardRepository.selectAll();//두개를 같이줘야함 상태코드랑 메시지
        return new ApiUtil<>(boardList); //MessageConvector 레스트컴버터면서 오브젝트일 때 발동 json으로 바뀐다
    }
}
