import styles from "./NotFound.module.css";
import Newnav from "../components/layout/NewNav";
import error from "../assets/404.png";

function NotFound() {

    return (
        <div style={{height:'100vh'}}>
             <Newnav/>
            <img class={styles.error_icon} src={error} alt="error" />
            <div class={styles.error1}>
                404 ERROR
            </div>
            <div class={styles.error2}>
                Page Not Found
            </div>
            <div class={styles.error3}>
                잘못된 주소입니다.
            </div>
        </div>
    );
}

export default NotFound;
