import Newnav from "../components/layout/NewNav";
import styles from "./Hall.module.css";
import dark from "../assets/darklogo.png"

function Hall() {

    return (
        <div style={{height:'100vh'}}>
            <Newnav/>
            <div className={styles.container} >
                <img id={styles.dark} src={dark} alt="dark"></img>
                <p className={styles.title}> 
                Hall &nbsp;of &nbsp;Fame</p>
                <div className={styles.cardbox}></div>
            </div>
        </div>
    );
}

export default Hall;