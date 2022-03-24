import styles from "./Nominee.module.css";


function Nominee({nominee, onDel}) {
    const {id, name, profile, thumbnail, imagePath1,imagePath2,imagePath3} = nominee


    return (        
        <div>
            <div id={styles.profile_div}>
                <img id={styles.thumbnail} src={thumbnail} alt="thumbnail" />
                <span id={styles.profile_name}> {name} </span>
                <button id={styles.profile_del} onClick={()=>onDel(id)}>삭제</button>
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