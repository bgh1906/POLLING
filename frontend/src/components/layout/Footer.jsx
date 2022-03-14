import styles from "./Footer.module.css";

function Footer() {
  return (
    <div className={styles.footer}>
      <div className={styles.company_Info}>
        <div className={styles.ci_contact}>POLLING</div>
        <div className={styles.ci_contents}>
          Monday - Friday
          <br />
          10:30 AM - 6:00 PM
          <br />
          010 - 2790 - 6642
          <br />
          contact@polling.com
        </div>
      </div>
      <div className={styles.ft_menu}>
        <ul>
          <li>이벤트</li>
          <li>자주 묻는 질문</li>
          <li>개인정보처리방침</li>
          <li>이용약관</li>
          <li>가이드</li>
          <li>고객센터</li>
        </ul>
        <ul>
          <li>Instagram</li>
          <li>Kakao</li>
          <li>Facebook</li>
          <li>Pinterest</li>
          <li>Youtube</li>
        </ul>
      </div>
      <div className={styles.ft_subInfo}>
        주식회사 폴링
        <br />
        Owner: 이현우 | CPO: 김혜란 | CTO: 서승원 | Business License:
        000-00-00000 | Online Biz: 2022-서울강남-0000 | Adress: 06220 서울특별시
        강남구 테헤란로 212 멀티캠퍼스 역삼 12층
      </div>
      <div className={styles.ft_copyright}>
        Copyright © 2022 POLLING. All Rights Reserved.
      </div>
    </div>
  );
}

export default Footer;
