import styles from "./Admin.module.css";
import { Link } from "react-router-dom";
import NewNav from "../components/layout/NewNav.jsx"
import { useEffect } from "react";
import { useSelector } from 'react-redux'
import Footer from "../components/layout/Footer";



function Admin() {

    useEffect(()=>{
        window.scrollTo(0,0);
    },[])
        
      

    return (
        <div>
            <div>
              <NewNav />
            </div>
            <div className={styles.title}>
                <div>
                    POLL MANAGEMENT
                </div>
                
            </div> 

            <div className={styles.create} >
                <Link to="/createpoll" className={styles.create}>
                    + CREATE A POLL
                </Link>
            </div>

{/* unapproved */}
        <div className={styles.container}>
            <div>
                <div id={styles.status_title}>UNAPPROVED</div>
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
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>투표 수정하기</button>
                            <button id={styles.status_button}>투표 승인하기</button>
                        </div>
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>투표 수정하기</button>
                            <button id={styles.status_button}>투표 승인하기</button>
                        </div>
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>투표 수정하기</button>
                            <button id={styles.status_button}>투표 승인하기</button>
                        </div>
                    </div>
                </div>    
            </div>
        </div>

{/* Stand by */}
        <div className={styles.container}>
            <div>
                <div id={styles.status_title}>STAND BY</div>
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
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>투표 수정하기</button>
                            <button id={styles.status_button}>투표 시작하기</button>
                        </div>
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>투표 수정하기</button>
                            <button id={styles.status_button}>투표 시작하기</button>
                        </div>
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>투표 수정하기</button>
                            <button id={styles.status_button}>투표 시작하기</button>
                        </div>
                    </div>
                </div>    
            </div>
        </div>

{/* In Progress */}

        <div className={styles.container}>
            <div>
                <div id={styles.status_title}>IN PROGRESS</div>
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
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>투표 내역 공개</button>
                            <button id={styles.status_button}>투표 종료하기</button>
                        </div>
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>투표 내역 공개</button>
                            <button id={styles.status_button}>투표 종료하기</button>
                        </div>
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>투표 내역 공개</button>
                            <button id={styles.status_button}>투표 종료하기</button>
                        </div>
                    </div>
                </div>    
            </div>
        </div>

{/* Closed */}
        <div className={styles.container} style={{marginBottom:"20vw"}}>
            <div>
                <div id={styles.status_title}>CLOSED</div>
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
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>NFT 발급하기</button>
                            <button id={styles.status_button}>HISTORY 추가</button>
                        </div>
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>NFT 발급하기</button>
                            <button id={styles.status_button}>HISTORY 추가</button>
                        </div>
                        <div id={styles.status_buttonbox}>
                            <button id={styles.status_button}>NFT 발급하기</button>
                            <button id={styles.status_button}>HISTORY 추가</button>
                        </div>
                    </div>
                </div>    
            </div>
        </div>            
        <Footer></Footer>
        </div>
    );
}

export default Admin;