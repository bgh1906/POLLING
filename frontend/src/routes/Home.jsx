import styles from "./Home.module.css";
import { Link } from "react-router-dom";
import Nav from "../components/layout/Nav.jsx"
import Newnav from "../components/layout/NewNav";
import Footer from "../components/layout/Footer";
import Textscroll from "../components/layout/Textscroll";

function Home() {

    return (
        <div>
            <div>
              <Newnav />
              {/* <Nav /> */}
            </div>

            <div className={styles.history_list}>
                    <p id={styles.list}> Pick  your  Star</p>
            </div>

        </div>
    );
}

export default Home;