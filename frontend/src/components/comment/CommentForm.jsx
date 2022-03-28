import styles from "./Comment.module.css"
import fight from "../../assets/fight.png";
import fight2 from "../../assets/fight2.png";
import { useState, useEffect } from "react";
import deleteimage from "../../assets/delete.png";
import deleteimage2 from "../../assets/delete2.png";
import axios from "axios";
import { useSelector } from 'react-redux'


function CommentForm({comment, memberId, commentId, renderCheck}) {

    const {memberNickname, content} = comment
    
    const token = useSelector((state)=>(state[0].token));
    const userid = useSelector((state)=>(state[0].userid)); 

    function deleteComment(){
        axios.delete(
            `http://j6a304.p.ssafy.io:8080/api/polls/candidates/comments/${commentId}`,
            {
                headers: {
                    "Authorization": token,
                }
            }
        )
        .then(() =>{
            console.log("delete 성공!!")
            renderCheck();
        })
        .catch(error => {
            console.log(error.response)
        });
    }

    
    
    return (

         <div>
            { commentId % 2 ===1?
            <p id={styles.nickname}><img id={styles.fight} src={fight} alt="fight"/>{memberNickname}
             {userid ===memberId&& <img onClick={deleteComment} id={styles.delete_button} src={deleteimage2} alt='delete_icon'/>} 
             </p>
             :  <p id={styles.nickname}><img id={styles.fight} src={fight2} alt="fight2"/>{memberNickname}
              {userid ===memberId&& <img onClick={deleteComment} id={styles.delete_button} src={deleteimage2} alt='delete_icon'/>}
             </p>}

            
            <p id={styles.comment_content}>
            {content}
            </p>
         </div>
    );
}

export default CommentForm;