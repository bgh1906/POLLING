import axios from "axios";
import styles from "./Comment.module.css"
import { useState, useEffect } from "react";
import CommentForm from "./CommentForm";



function CommentList({data, renderCheck}) {

    const comment_data = [...data].reverse()

    return (

         <div className={styles.commentlist_box}>
            <ul style={{ padding:"0" }}>
             {
                comment_data.map(comment => <CommentForm key={comment.commentId} commentId={comment.commentId} memberId={comment.memberId} comment={comment} renderCheck={renderCheck}/>)
             }
         </ul>
         </div>
    );
}

export default CommentList;