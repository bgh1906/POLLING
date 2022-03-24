import styles from "./Home.module.css";
import fame from "../../assets/fame.png"
import arrow7 from "../../assets/arrow7.png"
import { Link } from "react-router-dom";


function Hallmain({changeModeplus, changeModeminus}) {

    return (
        <div className={styles.homebox}>
            <div id={styles.sub_text_hall}>
                <p>Hall OF Fame</p>
            </div>
            <div>
                <p id={styles.main_text_hall}> Honoring</p>
                <p id={styles.main_text_hall2}> Winner</p>
            </div>
            <img id={styles.main_image3} src={fame} alt="fame" />
            <p id={styles.click3}>Click here</p>
            <Link to='/hall' id={styles.sub_text_hall2}> Go to Hall of Fame </Link>
            <button id={styles.arrow_button} onClick={changeModeminus}><img id={styles.arrow} src={arrow7} alt="arrow"/></button>
            <button id={styles.arrow_button} onClick={changeModeplus}><img id={styles.arrow2} src={arrow7} alt="arrow2"/></button> 
        </div>
    );
}

export default Hallmain;