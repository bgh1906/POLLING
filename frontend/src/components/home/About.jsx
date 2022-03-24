import styles from "./Home.module.css";
import block from "../../assets/block.png"
import arrow7 from "../../assets/arrow7.png"
import { Link } from "react-router-dom";


function About({changeModeplus, changeModeminus}) {

    return (
        <div className={styles.homebox}>
            <div id={styles.sub_text_about}>
                <p>ABOUT</p>
            </div>
            <div>
                <p id={styles.main_text_about}> Blockchain</p>
                <p id={styles.main_text_about2}> Based</p>
                <p id={styles.main_text_about3}> Vote</p>
            </div>
            <img id={styles.main_image} src={block} alt="block" />
            <p id={styles.click}>Click here</p>
            <Link to='/whypolling' id={styles.sub_text_about2}> Why POLLING? </Link>
            <button id={styles.arrow_button} onClick={changeModeminus}><img id={styles.arrow} src={arrow7} alt="arrow"/></button>
            <button id={styles.arrow_button} onClick={changeModeplus}><img id={styles.arrow2} src={arrow7} alt="arrow2"/></button> 
        </div>
    );
}

export default About;