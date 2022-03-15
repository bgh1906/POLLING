import styles from "./Home.module.css";
import { Link } from "react-router-dom";
import Nav from "../components/layout/Nav.jsx"


function Home() {

    return (
        <div>
            <div>
              <Nav />
            </div>
{/* 메인 이미지 */}
            <div className={styles.main}>
                <img 
                id={styles.mainimg}
                src="https://blog.kakaocdn.net/dn/vckff/btqCjeJmBHM/tMVpe4aUIMfH4nKS4aO3tK/img.jpg" alt="mainimg"/>
            </div>
{/* 투표리스트  */}
            <div className={styles.poll_list}>
                <div >
                    <div id={styles.list}>POLL LIST</div>
                    <div id={styles.bigbox}>
                        <div id={styles.box1}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <img id={styles.list_img} src="https://cdn.mhns.co.kr/news/photo/201608/21091_43510_93.JPG" alt="img2" />
                            <img id={styles.list_img} src="https://image.ytn.co.kr/general/jpg/2021/1210/202112100850018249_d.jpg" alt="img3" />
                        </div>
                        <div id={styles.box2}>
                            <div id={styles.list_text}>
                                <span > 제90회 전국춘향선발대회 </span> 
                            </div>
                            <div id={styles.list_text}>
                                <span > 프로듀스 101 </span>
                            </div>
                            <div id={styles.list_text}>
                                <span > 내일은 미스터 트롯 </span>
                            </div>
                        </div>
                        <div id={styles.box3}>
                            <div id={styles.list_date}>
                                <span > 2020.09.10 - 2020.09.13 </span> 
                            </div>
                            <div id={styles.list_date}>
                                <span > 2020.09.10 - 2020.09.13 </span>
                            </div>
                            <div id={styles.list_date}>
                                <span > 2020.09.10 - 2020.09.13 </span>
                            </div>
                        </div>
                        <div id={styles.box4}>
                        <Link to="/polllist">
                        <button id={styles.more}>더보기</button>
                        </Link>
                        </div>
                    </div>
                </div>
            </div>
{/* History */}
            <div className={styles.history_list}>
                <div>
                    <div id={styles.history}>HISTORY</div>
                    <div id={styles.bigbox}>
                        <div id={styles.box1}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <img id={styles.list_img} src="https://cdn.mhns.co.kr/news/photo/201608/21091_43510_93.JPG" alt="img2" />
                            <img id={styles.list_img} src="https://image.ytn.co.kr/general/jpg/2021/1210/202112100850018249_d.jpg" alt="img3" />
                        </div>
                        <div id={styles.box2}>
                            <div id={styles.list_text}>
                                <span > 제90회 전국춘향선발대회 </span> 
                            </div>
                            <div id={styles.list_text}>
                                <span > 프로듀스 101 </span>
                            </div>
                            <div id={styles.list_text}>
                                <span > 내일은 미스터 트롯 </span>
                            </div>
                        </div>
                        <div id={styles.box3}>
                            <div id={styles.list_date}>
                                <span > 2020.09.10 - 2020.09.13 </span> 
                            </div>
                            <div id={styles.list_date}>
                                <span > 2020.09.10 - 2020.09.13 </span>
                            </div>
                            <div id={styles.list_date}>
                                <span > 2020.09.10 - 2020.09.13 </span>
                            </div>
                        </div>
                        <div id={styles.box4}>
                    <Link to="/history">
                      <button id={styles.more}>더보기</button>
                    </Link>
                    </div>
                    </div>
                </div>
            </div>

        </div>

    );
}

export default Home;