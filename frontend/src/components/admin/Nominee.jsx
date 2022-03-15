import styles from "./Nominee.module.css";


function Nominee({nominee, onDel}) {
    const {id, name, profile, profile_image, additional_image1,additional_image2,additional_image3} = nominee


    return (        
        <div>
            <div id={styles.profile_div}>
                <img id={styles.profile_image} src={profile_image} alt="profile_image" />
                <span id={styles.profile_name}> {name} </span>
                <button id={styles.profile_del} onClick={()=>onDel(id)}>삭제</button>
                <div id={styles.profile_desc}>{profile} </div>
            </div>
            <div id={styles.profile_gallery}>
                <span> [ 참가자  갤러리 ]</span>
            </div>
            <div id={styles.add_div}>
                {additional_image1 !== "" && (
                    <img id={styles.add_image} src={additional_image1} alt="additional_image1" />
                    )}
                {additional_image2 !== "" && (
                    <img id={styles.add_image} src={additional_image2} alt="additional_image2" />
                    )}
                {additional_image3 !== "" && (
                    <img id={styles.add_image} src={additional_image3} alt="additional_image3" />
                    )}
            </div>   
        </div>
    );
}

export default Nominee;