import styles from "./Home.module.css";
import compe from "../../assets/compe.png"
import arrow from "../../assets/arrow.png"
import { Link } from "react-router-dom";


function PollListmain({changeModeplus, changeModeminus}) {

    return (
        <div className={styles.homebox}>
            <div id={styles.sub_text_compe}>
                <p>POLL</p>
            </div>
            <div>
                <p id={styles.main_text_compe}> Contest</p>
                <p id={styles.main_text_compe2}> In Progress</p>
            </div>
            <img id={styles.main_image5} src={compe} alt="compe" />
            <p id={styles.click5}>Click here</p>
            <Link to='/polllist' id={styles.sub_text_compe2}> Go to PollList </Link>
            <button id={styles.arrow_button} onClick={changeModeminus}><img id={styles.arrow} src={arrow} alt="arrow"/></button>
            <button id={styles.arrow_button} onClick={changeModeplus}><img id={styles.arrow2} src={arrow} alt="arrow2"/></button> 
        </div>
    );
}

export default PollListmain;