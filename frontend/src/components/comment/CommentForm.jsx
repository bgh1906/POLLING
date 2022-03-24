import styles from "./Comment.module.css"




function CommentForm({comment}) {

    const {userName, content} = comment


    
    return (

         <div>
            <p id={styles.nickname}>{userName}</p>
            <p id={styles.comment_content}>
            {content}
            </p>
         </div>
    );
}

export default CommentForm;