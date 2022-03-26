import { useState } from "react";
import styles from "./Comment.module.css"
import Button from '@mui/material/Button';
import axios from "axios";
import CommentList from "./CommentList";
import cheer from "../../assets/cheer.png";



function Comment() {

    const [ripple, setRipple] = useState("")
  
    function changeInput(e){
        setRipple(e.target.value)
    }

    function onSubmit(e){
        e.preventDefault()
        console.log(ripple)
        setRipple("")
        axios
        .post(
            "http://j6a304.p.ssafy.io:8080/api/candidates/comment",
            {
                "candidateId": 0,
                "content": {ripple}
            },
            {
                headers: {
                    Authorization:"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY0ODA1ODk2NSwiZXhwIjoxNjQ4MDYwNzY1fQ.8o-Qm4UN9gvc0Jsb6LU5Ge7pHvAHL2HrD3W3BQ6W2RM"
                },
            }
        )
        .then((res) =>{
            console.log(res)
        })
        .catch((e) =>{
            console.error(e);
        });

    };

    return (
        <div className={styles.comment_box}> 
            <div id={styles.message}><img id={styles.cheer} src={cheer} alt="cheer"/>응원 메시지</div>
            <form id={styles.comment_form} onSubmit={onSubmit}>
                <textarea id={styles.comment_input} type="text" onChange={changeInput} value={ripple} name="ripple" maxLength="100"
                    placeholder="응원 메시지를 적어주세요~!"
                ></textarea>
                <Button id={styles.register_button} type="submit" variant="contained">댓글 등록</Button>
            </form>
            <CommentList></CommentList>
        </div>
    );
}

export default Comment;