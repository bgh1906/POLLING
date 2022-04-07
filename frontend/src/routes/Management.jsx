import { Link } from "react-router-dom";
import Footer from "../components/layout/Footer.jsx";
import Styles from "./Management.module.css";
import NewNav from "../components/layout/NewNav.jsx";

function Management() {

    return (
        <div style={{height:'100vh'}}>
            <NewNav/>
            <div className={Styles.management}>Management</div>
            <div>
                   <Link to='/user' className={Styles.user}><img src={'https://img.icons8.com/external-kiranshastry-lineal-kiranshastry/160/000000/external-user-advertising-kiranshastry-lineal-kiranshastry-4.png'} style={{height:'25vh', width:'12vw'}}  alt="mark" className={Styles.mark} /> User Mgt </Link>
                <Link to='/admin' className={Styles.poll}><img src={'https://img.icons8.com/ios/160/000000/ballot--v2.png'} style={{height:'25vh', width:'12vw'}}  alt="mark" className={Styles.mark} /> Poll Mgt </Link>
            </div>
        </div>
    );
}

export default Management;