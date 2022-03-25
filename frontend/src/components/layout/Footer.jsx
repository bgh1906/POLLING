import { useEffect, useState } from "react";
import styles from "./Footer.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowUp } from "@fortawesome/free-solid-svg-icons";

function Footer() {
  const [showButton, setShowButton] = useState(false);

  useEffect(() => {
    let iscompomount = true;

    window.addEventListener("scroll", () => {
      if (iscompomount) {
        if (window.pageYOffset > 300) {
          setShowButton(true);
        } else {
          setShowButton(false);
        }
      }
    });
    return () => {
      iscompomount = false;
    };
  }, []);

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth", // for smoothly scrolling
    });
  };

  return (
    <>
      <div className={styles.footer}>
        <div className={styles.ft_company}>
          <div className={styles.ft_Name}>POLLING</div>
          <div className={styles.ft_Info}>
            <div className={styles.ft_Address}>
              Multicampus, 12F,
              <br />
              212, Teheran-ro,
              <br />
              Gangnam-gu, Seoul
            </div>
            <div className={styles.ft_Contact}>
              <ul>
                <li>T. 010-2790-6642</li>
                <li>10:00 AM - 06:00 PM</li>
                <li>M. contact@polling.com</li>
              </ul>
            </div>
          </div>
        </div>
        <span className={styles.ft_verticalline}></span>
        <div className={styles.ft_right1}>
          <div className={styles.ft_itemTitle}>About</div>
          <ul>
            <li>Guide</li>
            <li>FAQ</li>
            <li>Privacy Policy</li>
            <li>Terms & Conditions</li>
          </ul>
        </div>
        <div className={styles.ft_right2}>
          <div className={styles.ft_itemTitle}>Follow Us</div>
          <ul>
            <li>Instagram</li>
            <li>Facebook</li>
            <li>Twitter</li>
          </ul>
        </div>
        <div className={styles.ft_right3}>
          <div className={styles.ft_itemTitle}>Languages</div>
          <ul>
            <li>Korean</li>
            <li>English</li>
          </ul>
        </div>
        <div className={styles.ft_copyright}>
          Copyright © 2022 POLLING. All Rights Reserved.
        </div>
      </div>
      {showButton && (
        <FontAwesomeIcon
          icon={faArrowUp}
          onClick={scrollToTop}
          className={styles.backtotop}
        />
      )}
    </>
  );
}

export default Footer;
