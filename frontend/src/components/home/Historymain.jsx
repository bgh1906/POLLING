import styles from "./Home.module.css";
import history from "../../assets/history.png"
import arrow7 from "../../assets/arrow7.png"
import { Link } from "react-router-dom";


function Historymain({changeModeplus, changeModeminus}) {

    return (
        <div className={styles.homebox}>
            <div id={styles.sub_text_history}>
                <p>HISTORY</p>
            </div>
            <div>
                <p id={styles.main_text_history}> Contest</p>
                <p id={styles.main_text_history2}> Closed</p>
            </div>
            <img id={styles.main_image2} src={history} alt="history" />
            <p id={styles.click2}>Click here</p>
            <Link to='/history' id={styles.sub_text_history2}> Go to History </Link>
            <button id={styles.arrow_button} onClick={changeModeminus}><img id={styles.arrow} src={arrow7} alt="arrow"/></button>
            <button id={styles.arrow_button} onClick={changeModeplus}><img id={styles.arrow2} src={arrow7} alt="arrow2"/></button> 
        </div>
    );
}

export default Historymain;