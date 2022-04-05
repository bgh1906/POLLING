import Newnav from "../components/layout/NewNav";
import styles from "./Hall.module.css";
import dark from "../assets/darklogo.png"

function Hall() {

    const famedata = [
        {
            img:"https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMTM5/MDAxNjExNDgzMjkyNDcz.TUY0uS9RlcoOzGV1gFjamo2KKqtRjMauEoOIttuoLPwg.FtRvklMLIhjdlZIN6dkqxpfgGjrjpi_8wFhChENt12Ig.JPEG.yetall_0522/1611483290046.jpg?type=w800",
            name:"지헌",
            poll:"아이돌학교"
        },
        {
            img:"https://blog.kakaocdn.net/dn/DPDsG/btqJ1LYvkyG/6qifiqBgq9pkh7n1YbeAaK/img.png",
            name:"수지",
            poll:"미스에이"
        },
        {
            img:"https://blog.kakaocdn.net/dn/WOUaO/btqDn2sq6bg/CmvxgcyD8WwXmRqZOXnYkK/img.jpg",
            name:"김세정",
            poll:"프로듀스 101"
        },
        {
            img:"https://mblogthumb-phinf.pstatic.net/MjAyMDA4MTlfMjEw/MDAxNTk3ODE1Nzk5MDY3.Edp8_KUuxwubMX5BS24SznGcuBNpsD_cvcPw5-XgCusg.JFnJx-ZS-UDSDnCGWaVO7eQd8QrlOMcUbPg-e7xAIdYg.JPEG.yetall_0522/1597815796929.jpg?type=w800",
            name:"슬기",
            poll:"레드벨벳"
        },
        {
            img:"https://cdnimg.melon.co.kr/cm2/artistcrop/images/002/61/143/261143_20200508100949_500.jpg?8671d6593fd8038301e96ebcd9a8fd62/melon/resize/416/quality/80/optimize",
            name:"아이유",
            poll:"아이유"
        },
        {
            img:"https://mblogthumb-phinf.pstatic.net/MjAyMDAzMDFfMjMw/MDAxNTgzMDM1MTkzODY1.CkwBjnQjoxm8-1h4Zi-Ehv48hCqx7Cnn3kHKp35LgzUg.oA035QUGG2zs9V_geG0Zs4zLB99nbNrVZWloua3UWxkg.JPEG.maymaysa/ERdE_WpU0AIO7qm.jpg?type=w800",
            name:"뷔",
            poll:"방탄소년단"
        },
        {
            img:"https://pbs.twimg.com/profile_images/1070062166168891392/Ex8cIys2_400x400.jpg",
            name:"온유",
            poll:"샤이니"
        },
        {
            img:"https://t1.daumcdn.net/cfile/tistory/990F72365A71858B09",
            name:"정해인",
            poll:"프로듀스 101"
        },
        {
            img:"https://scontent-gmp1-1.xx.fbcdn.net/v/t1.6435-9/35549808_1876551835978673_3816356485793841152_n.png?stp=dst-png_p320x320&_nc_cat=107&ccb=1-5&_nc_sid=85a577&_nc_ohc=ef4ie25lhwkAX9k8hqd&_nc_ht=scontent-gmp1-1.xx&oh=00_AT_YV-6xSXOOJPfuA68QQQWWS_5KkYYAzz1927hoL_kY1w&oe=626F67A2",
            name:"강다니엘",
            poll:"프로듀스 101"
        },
        {
            img:"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/%EC%9E%84%EC%98%81%EC%9B%85_Lim_Young-woong_01.jpg/800px-%EC%9E%84%EC%98%81%EC%9B%85_Lim_Young-woong_01.jpg",
            name:"임영웅",
            poll:"미스터트롯"
        }
    ]




    return (
        <div style={{height:'100vh'}}>
            <Newnav/>
            <div className={styles.container} >
                <img id={styles.dark} src={dark} alt="dark"></img>
                <p className={styles.title}> 
                Hall &nbsp;of &nbsp;Fame</p>
                <div className={styles.cardbox}>
                    {famedata.map((card,index)=> 
                        <div id={styles.card} key={index}>
                            <img id={styles.dark2} src={dark} alt="dark2"></img>
                            <img id={styles.profile_image}src={card.img} alt="image1" />
                            <div id={styles.text_box}>
                                <span id={styles.name}>{card.name}</span> <br/>
                                <span id={styles.poll}>{card.poll}</span>
                            </div>
                            <div>
                                <div id={styles.polling}>POLLING</div>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}

export default Hall;