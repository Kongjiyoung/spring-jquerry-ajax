package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController { //ajax를 위한 컨트롤러 따로 만듬
    private final BoardRepository boardRepository;

    @PutMapping("/api/boards/{id}")
    public ApiUtil<?> update(@PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO requestDTO){//requestbody json데이터를 받을 수 있음
        boardRepository.update(id, requestDTO);
        Board board = boardRepository.selectOne(id);
        System.out.println(board);



        return new ApiUtil<>(null);
    }


    @PostMapping("/api/boards")
    public ApiUtil<?> write(@RequestBody BoardRequest.WriteDTO requestDTO){//requestbody json데이터를 받을 수 있음
        boardRepository.insert(requestDTO);
        return new ApiUtil<>(null);
    }

    @DeleteMapping(value = "/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id, HttpServletResponse response){
        Board board = boardRepository.selectOne(id);
        if(board ==null){
            response.setStatus(404);
            return new ApiUtil<>(404, "해당게시글을 찾을 수 없습니다");
        }
        boardRepository.deleteById(id);

        return  new ApiUtil<>(null);
    }

    @GetMapping(value = "/api/boards") //json으로 보는 주소
    public ApiUtil<?> findAll(HttpServletResponse response){ //?를 넣으면 알아서 맞춰줌
        //response.setStatus(401);
        List<Board> boardList = boardRepository.selectAll();//두개를 같이줘야함 상태코드랑 메시지
        return new ApiUtil<>(boardList); //MessageConvector 레스트컴버터면서 오브젝트일 때 발동 json으로 바뀐다
    }



}
