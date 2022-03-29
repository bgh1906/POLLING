import styles from "./Nominee.module.css";
import Button from '@mui/material/Button';


function Nominee({nominee, onDel, onEdit, deleteCandi}) {
    const {id, name, profile, thumbnail, imagePath1,imagePath2,imagePath3} = nominee

    return (        
        <div>
            <div id={styles.profile_div}>
                <img id={styles.thumbnail} src={thumbnail} alt="thumbnail" />
                <span id={styles.profile_name}> {name} </span>
                <Button id={styles.profile_update} onClick={()=>{
                    onEdit(nominee)
                   }
                    } variant="contained">수정</Button>
                <Button id={styles.profile_del} onClick={()=>{
                deleteCandi(id)}
                } variant="contained">삭제</Button>
                <div id={styles.profile_desc}>{profile} </div>
            </div>
            <div id={styles.profile_gallery}>
                <span> [ 참가자  갤러리 ]</span>
            </div>
            <div id={styles.add_div}>
                {imagePath1 !== "" && (
                    <img id={styles.add_image} src={imagePath1} alt="imagePath1" />
                    )}
                {imagePath2 !== "" && (
                    <img id={styles.add_image} src={imagePath2} alt="imagePath2" />
                    )}
                {imagePath3 !== "" && (
                    <img id={styles.add_image} src={imagePath3} alt="imagePath3" />
                    )}
            </div>
            <div id={styles.distinct_box}></div>   
        </div>
    );
}

export default Nominee;