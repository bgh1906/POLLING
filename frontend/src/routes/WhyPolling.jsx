import NewNav from "../components/layout/NewNav.jsx";
import Styles from "./WhyPolling.module.css";

function WhyPolling() {

    return (

        <>
            <NewNav />
            <div className={Styles.WhyPolling}> Why POLLING?</div>
            <div className={Styles.longtext}>
                : POLLING에서 구축한 블록체인 온라인 투표 시스템은, <br />
                &nbsp;블록체인에 투표데이터 등을 기록함으로써 위&middot;변조가 불가능하고, 이해관계자가 투&middot;개표 결과를 스스로 검증할 수 있습니다.
            </div>
            <div className={Styles.box}>
                <div className={Styles.boxinbox}>
                    블록체인 온라인투표 시스템
                </div>
            </div>
            <div className={Styles.outlinecircle1}>
                <div className={Styles.inlinecircle1}>
                    <img src="https://img.icons8.com/external-flaticons-lineal-color-flat-icons/150/000000/external-voter-politics-flaticons-lineal-color-flat-icons.png" className={Styles.img1} />
                </div>
                {/* <img src="https://img.icons8.com/external-flaticons-lineal-color-flat-icons/90/000000/external-voter-politics-flaticons-lineal-color-flat-icons.png" className={Styles.voterimg}/> */}
            </div>
            <div className={Styles.voter}>유권자</div>
            {/* 여기 화살표 넣고 */}
            <img src="https://img.icons8.com/ios/70/000000/long-arrow-right.png" className={Styles.arrow}/>
            <div className={Styles.text}>본인 인증</div>
            <div className={Styles.outlinecircle2}>
                <div className={Styles.inlinecircle2}>
                    <img src="https://img.icons8.com/external-colorful-filled-outline-dmitry-mirolyubov/130/000000/external-blockchain-bitcoin-and-mining-colorful-filled-outline-dmitry-mirolyubov-10.png" className={Styles.img2} />
                </div>
            </div>
            <div className={Styles.block}>
                위변조가 불가능한 <br />
                블록체인에 <br />
                투표데이터 기록
            </div>
            {/* 여기 화살표 넣고 */}
            <img src="https://img.icons8.com/ios/70/000000/long-arrow-right.png" className={Styles.arrow2}/>
            <div className={Styles.outlinecircle3}>
                <div className={Styles.inlinecircle3}>
                    <img src="https://img.icons8.com/external-ddara-flat-ddara/140/000000/external-verification-fintech-ddara-flat-ddara.png" className={Styles.img3}  />
                </div>
            </div>
            <div className={Styles.result}>
                투표 결과 <br />
                직접 검증
            </div>
        </>
    );
}

export default WhyPolling;