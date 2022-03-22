import styles from "./Admin.module.css";
import { Link } from "react-router-dom";
import NewNav from "../components/layout/NewNav.jsx"
import { useEffect } from "react";
import { useSelector } from 'react-redux'
import Footer from "../components/layout/Footer";
import { Fade } from "react-awesome-reveal";
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Moment from 'react-moment';
import logo from "../assets/mark_slim.png"



function Admin() {

    // useEffect(()=>{
    //     window.scrollTo(0,0);
    // },[])
        
      

    return (
        <div>
            <div>
              <NewNav />
            </div>
{/* Header */}
            <div id={styles.title_header}>
                <div className={styles.title}>
                    <div >
                    <div style={{ textAlign:"center"}}>POLL</div>
                    <div>MANAGEMENT </div>
                    </div>
                    
                </div> 

                <div className={styles.create} >
                    <Link to="/createpoll" className={styles.create}>
                        CREATE A POLL
                    </Link>
                </div>
                <Moment id={styles.moment}></Moment>
            </div>

        <div className={styles.container}>
            <div style={{width:"100%"}}>
                <div id={styles.status}>POLL STATUS</div>
                <img id={styles.logo} src={logo} alt="logo" />

{/* unapproved */}
                <div id={styles.status_title}>UNAPPROVED
                    <div id={styles.status_kor}> 미승인 </div>
                </div>
                <Grid id={styles.poll_container} container>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 수정하기</button></div>
                            <div><button id={styles.status_button2}>투표 승인하기</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 수정하기</button></div>
                            <div><button id={styles.status_button2}>투표 승인하기</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 수정하기</button></div>
                            <div><button id={styles.status_button2}>투표 승인하기</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 수정하기</button></div>
                            <div><button id={styles.status_button2}>투표 승인하기</button></div>
                    </Grid>
                </Grid>
                <div id={styles.status_under}></div>  
{/* stand by */}
                <div id={styles.status_title}>STAND BY
                    <div id={styles.status_kor}> 대기 </div>
                </div>
                <Grid id={styles.poll_container} container>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 수정하기</button></div>
                            <div><button id={styles.status_button2}>투표 시작하기</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 수정하기</button></div>
                            <div><button id={styles.status_button2}>투표 시작하기</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 수정하기</button></div>
                            <div><button id={styles.status_button2}>투표 시작하기</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 수정하기</button></div>
                            <div><button id={styles.status_button2}>투표 시작하기</button></div>
                    </Grid>
                </Grid>
                <div id={styles.status_under}></div>
{/* In progress */}       
                <div id={styles.status_title}>IN PROGRESS
                    <div id={styles.status_kor}> 진행중 </div>
                </div>
                <Grid id={styles.poll_container} container>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 옵션변경</button></div>
                            <div><button id={styles.status_button2}>투표 종료하기</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 옵션변경</button></div>
                            <div><button id={styles.status_button2}>투표 종료하기</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 옵션변경</button></div>
                            <div><button id={styles.status_button2}>투표 종료하기</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>투표 옵션변경</button></div>
                            <div><button id={styles.status_button2}>투표 종료하기</button></div>
                    </Grid>
                </Grid>
                <div id={styles.status_under}></div>     
{/* history */}       
                <div id={styles.status_title}> CLOSED
                    <div id={styles.status_kor}> 종료 </div>
                </div>
                <Grid id={styles.poll_container} container>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>NFT 발급</button></div>
                            <div><button id={styles.status_button2}>HISTORY 추가</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>NFT 발급</button></div>
                            <div><button id={styles.status_button2}>HISTORY 추가</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>NFT 발급</button></div>
                            <div><button id={styles.status_button2}>HISTORY 추가</button></div>
                    </Grid>
                    <Grid id={styles.pollbox} xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><button id={styles.status_button1}>NFT 발급</button></div>
                            <div><button id={styles.status_button2}>HISTORY 추가</button></div>
                    </Grid>
                </Grid>
                <div id={styles.status_under}></div>     

                {/* <div id={styles.status_title}>UNAPPROVED</div>
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
                </div>             */}

{/* Stand by */}
        {/* <Fade>
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
        </Fade> */}

{/* In Progress */}
        {/* <Fade>
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
        </Fade> */}

{/* Closed */}

        {/* <Fade>
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
        </Fade>   */}
        </div>
</div>

        <Footer></Footer>
        </div>
    );
}

export default Admin;