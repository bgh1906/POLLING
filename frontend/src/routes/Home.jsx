import styles from "./Home.module.css";
import { Link } from "react-router-dom";
import Nav from "../components/layout/Nav.jsx"
import Footer from "../components/layout/Footer";
import Textscroll from "../components/layout/Textscroll";
import Newnav from "../components/layout/NewNav";

function Home() {

    return (
        <div>
            <div>
              <Newnav />
            </div>
{/* 메인 이미지 */}
            {/* <div className={styles.main}> */}
                {/* <Textscroll></Textscroll> */}
                {/* <img 
                id={styles.mainimg}
                src="https://www.biblicaltraining.org/sites/default/files/poll.jpg" alt="mainimg"/> */}
            {/* </div> */}
{/* 투표리스트  */}
            <div className={styles.poll_list}>
                <div >
                    <p id={styles.list}> Pick  your  Star</p>
                </div>
            </div>

            {/* <Footer></Footer> */}
        </div>
    );
}

export default Home;