import Footer from "../components/layout/Footer";
import styles from "./History.module.css";
import chunhyang from "../assets/chunhyang.PNG";
import apart from "../assets/apart.PNG";
import ev9 from "../assets/ev9.PNG";
import kClassic from "../assets/kClassic.PNG";
import logoContest from "../assets/logoContest.PNG";
import missKorea from "../assets/missKorea.PNG";
import mrKorea from "../assets/mrKorea.PNG";
import muscleMania from "../assets/muscleMania.PNG";
import { useState } from "react";
import Newnav from "../components/layout/NewNav";

function History() {
  const [modalShow, setModalShow] = useState(false);
  return (
    <div>
      <Newnav />
      <div className={styles.history_title}>History</div>
      <div className={styles.contents}>
        <div className={styles.history_container}>
          <div className={styles.history_itemlist}>
            <div className={styles.history_item}>
              <img
                src={chunhyang}
                alt="img1"
                // onClick={() => setModalShow(true)}
              />
              <figcaption>
                <span>2020</span>
                춘향제
              </figcaption>
            </div>
            <div className={styles.history_item}>
              <img src={apart} alt="img2" />
              <figcaption>
                <span>2020</span>
                춘향제
              </figcaption>
            </div>
            <div className={styles.history_item}>
              <img src={ev9} alt="img3" />
              <figcaption>
                <span>2020</span>
                춘향제
              </figcaption>
            </div>
            <div className={styles.history_item}>
              <img src={kClassic} alt="img4" />
              <figcaption>
                <span>2020</span>
                춘향제
              </figcaption>
            </div>
            <div className={styles.history_item}>
              <img src={logoContest} alt="img5" />
              <figcaption>
                <span>2020</span>
                춘향제
              </figcaption>
            </div>
            <div className={styles.history_item}>
              <img src={missKorea} alt="img6" />
              <figcaption>
                <span>2020</span>
                춘향제
              </figcaption>
            </div>
            <div className={styles.history_item}>
              <img src={mrKorea} alt="img7" />
              <figcaption>
                <span>2020</span>
                춘향제
              </figcaption>
            </div>
            <div className={styles.history_item}>
              <img src={muscleMania} alt="img8" />
              <figcaption>
                <span>2020</span>
                춘향제
              </figcaption>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default History;
