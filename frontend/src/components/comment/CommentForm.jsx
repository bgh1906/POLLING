import styles from "./Comment.module.css"
import fight from "../../assets/fight.png";
import fight2 from "../../assets/fight2.png";
import { useState, useEffect } from "react";
import deleteimage from "../../assets/delete.png";
import deleteimage2 from "../../assets/delete2.png";
import axios from "axios";



function CommentForm({comment, memberId, commentId, renderCheck}) {

    const {memberNickname, content} = comment
    const userid = 11;

    function deleteComment(){
        axios.delete(
            `http://j6a304.p.ssafy.io:8080/api/polls/candidates/comments/${commentId}`,
            {
                headers: {
                    "Authorization":"bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMSIsInJvbGVzIjpbIlJPTEVfQ09NUEFOWSIsIlJPTEVfVVNFUiJdLCJpYXQiOjE2NDgzOTM5MjIsImV4cCI6MTY0ODM5NTcyMn0.yCU2JEhUbcrBfbMvFkzlnwU4fQ38s1Rfw-38EpgSmgg",
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