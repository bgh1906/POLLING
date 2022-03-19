import Footer from "../components/layout/Footer";
import Nav from "../components/layout/Nav";
import styles from "./PollList.module.css";
import chunhyang from "../assets/chunhyang.PNG";
import apart from "../assets/apart.PNG";
import ev9 from "../assets/ev9.PNG";
import kClassic from "../assets/kClassic.PNG";
import logoContest from "../assets/logoContest.PNG";
import missKorea from "../assets/missKorea.PNG";
import mrKorea from "../assets/mrKorea.PNG";
import muscleMania from "../assets/muscleMania.PNG";
import snack from "../assets/snack.PNG";
import snack2 from "../assets/snack2.PNG";
import golf from "../assets/golf.PNG";

function PollList() {
  return (
    <div className={styles.polllist}>
      <Nav />
      <div className={styles.list_title}>Poll List</div>
      <div className={styles.list_container}>
        <img src={chunhyang} alt="img1" className={styles.list_item} />
        <img src={apart} alt="img2" className={styles.list_item} />
        <img src={ev9} alt="img3" className={styles.list_item} />
        <img src={kClassic} alt="img4" className={styles.list_item} />
        <img src={logoContest} alt="img5" className={styles.list_item} />
        <img src={missKorea} alt="img6" className={styles.list_item} />
        <img src={mrKorea} alt="img7" className={styles.list_item} />
        <img src={muscleMania} alt="img8" className={styles.list_item} />
        <img src={snack} alt="img9" className={styles.list_item} />
        <img src={snack2} alt="img10" className={styles.list_item} />
        <img src={golf} alt="img11" className={styles.list_item} />
      </div>
      <Footer />
    </div>
  );
}

export default PollList;
