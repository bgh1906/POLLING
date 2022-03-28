import Nav from "../components/layout/Nav";
import Styles from "./Mypage.module.css";


function Mypage() {

    return (
        <div>
            <Nav />

            <div className={Styles.mypage}> My page </div>

            <div class="tabs-head">
                <span data-tab="tab-1" class="tabs-nav">내 정보 수정</span>
                <span data-tab="tab-2" class="tabs-nav">투표 내역</span>
                <span data-tab="tab-3" class="tabs-nav">1:1 문의</span>
                </div>

                <div class="tabs-content">
                <div id="tab-1" class="b-tab active">
                    
                    {/* <!-- 여기에 첫 번째 탭의 콘텐츠를 작성! --> */}
                    
                </div>
                <div id="tab-2" class="b-tab">
                    
                    {/* <!-- 여기에 두 번째 탭의 콘텐츠를 작성! --> */}
                    
                </div>
                <div id="tab-3" class="b-tab">
                    
                    {/* <!-- 여기에 세 번째 탭의 콘텐츠를 작성! --> */}
                    
                </div>
</div>



        </div>
    );
}

export default Mypage;