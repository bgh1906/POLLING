import styles from "./Comment.module.css"
import fight from "../../assets/fight.png";
import fight2 from "../../assets/fight2.png";
import { useState, useEffect } from "react";




function CommentForm({comment, id}) {

    const {userName, content} = comment

    
    
    return (

         <div>
            { id%2 ===1?
            <p id={styles.nickname}><img id={styles.fight} src={fight} alt="fight"/>{userName}</p>
             :  <p id={styles.nickname}><img id={styles.fight} src={fight2} alt="fight2"/>{userName}</p>}
            <p id={styles.comment_content}>
            {content}
            </p>
         </div>
    );
}

export default CommentForm;