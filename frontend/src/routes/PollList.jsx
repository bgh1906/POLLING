import Footer from "../components/layout/Footer";
import Nav from "../components/layout/Nav";
import styles from "./PollList.module.css";
import apart from "../assets/apart.PNG";
import chunhyang from "../assets/chunhyang.PNG";
import ev9 from "../assets/ev9.PNG";
import kClassic from "../assets/kClassic.PNG";
import logoContest from "../assets/logoContest.PNG";
import missKorea from "../assets/missKorea.PNG";
import mrKorea from "../assets/mrKorea.PNG";
import muscleMania from "../assets/muscleMania.PNG";
import snack from "../assets/snack.PNG";
import snack2 from "../assets/snack2.PNG";

function PollList() {
  return (
    <div>
      <Nav />
      <div className={styles.list_title}>PollList</div>
      <div className={styles.list_container}>
        <img src={apart} alt="apartment naming" className={styles.list_item} />
        <img
          src={chunhyang}
          alt="apartment naming"
          className={styles.list_item}
        />
        <img src={ev9} alt="apartment naming" className={styles.list_item} />
        <img
          src={kClassic}
          alt="apartment naming"
          className={styles.list_item}
        />
        <img
          src={logoContest}
          alt="apartment naming"
          className={styles.list_item}
        />
        <img
          src={missKorea}
          alt="apartment naming"
          className={styles.list_item}
        />
        <img
          src={mrKorea}
          alt="apartment naming"
          className={styles.list_item}
        />
        <img
          src={muscleMania}
          alt="apartment naming"
          className={styles.list_item}
        />
        <img src={snack} alt="apartment naming" className={styles.list_item} />
        <img src={snack2} alt="apartment naming" className={styles.list_item} />
      </div>
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br /> <Footer />
    </div>
  );
}

export default PollList;
