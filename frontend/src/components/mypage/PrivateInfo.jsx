import * as React from 'react';
import PropTypes from 'prop-types';
import Button from '@mui/material/Button';
import { styled } from '@mui/material/styles';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import Typography from '@mui/material/Typography';
import Styles2 from "../../routes/Join2.module.css";
// import Styles from './Private.module.css';

const BootstrapDialog = styled(Dialog)(({ theme }) => ({
    '& .MuiDialogContent-root': {
      padding: theme.spacing(2),
    },
    '& .MuiDialogActions-root': {
      padding: theme.spacing(1),
    },
  }));
  
  const BootstrapDialogTitle = (props) => {
    const { children, onClose, ...other } = props;
  
    return (
      <DialogTitle sx={{ m: 0, p: 2 }} {...other}>
        {children}
        {onClose ? (
          <IconButton
            aria-label="close"
            onClick={onClose}
            sx={{
              position: 'absolute',
              right: 8,
              top: 8,
              color: (theme) => theme.palette.grey[500],
            }}
          >
            <CloseIcon />
          </IconButton>
        ) : null}
      </DialogTitle>
    );
  };
  
  BootstrapDialogTitle.propTypes = {
    children: PropTypes.node,
    onClose: PropTypes.func.isRequired,
  };
  
  export default function PrivateInfo() {
    const [open, setOpen] = React.useState(false);
  
    const handleClickOpen = () => {
      setOpen(true);
    };
    const handleClose = () => {
      setOpen(false);
    };
// function PrivateInfo({closePrivate}) {
    

    return(
        <div>
            {/* <Button onClick={handleClickOpen} className={Styles2.privatebtn} style={{fontSize:"0.8vw", color:"#9F824D", left:'2vw',fontFamily:"font"}} > */}
            {/* <button onClick={handleClickOpen}  style={{left:'4.55vw', fontSize:'2.5vh', fontFamily:''}}> */}
            <button onClick={handleClickOpen} className={Styles2.privacybtn} >
            {" "}Polling 개인정보처리방침{" "}
            </button>
            <Dialog
                onClose={handleClose}
                aria-labelledby="customized-dialog-title"
                open={open}
                maxWidth='md'
                PaperProps={{ sx: { backgroundColor:"#f8f2f5"  }}}
            >
        <BootstrapDialogTitle id="customized-dialog-title" onClose={handleClose} >
            <h>Polling 개인정보처리방침</h>
        </BootstrapDialogTitle>

            <DialogContent dividers>
            <Typography gutterBottom>
                    1. 개인정보의 처리 목적
                    <br />
                    Polling&#40;‘https://Polling.com’이하 'Polling'&#41; 은 다음의 목적을 위하여 개인정보를 처리하고 있으며, 다음의 목적 이외의 용도로는 이용하지 않습니다.
                    <br />- 고객 가입의사 확인, 고객에 대한 서비스 제공에 따른 본인 식별.인증, 회원자격 유지.관리, 물품 또는 서비스 공급에 따른 금액 결제, 물품 또는 서비스의 공급.배송 등
                </Typography>
                <br /><br />
                <Typography gutterBottom>
                    2. 개인정보의 처리 및 보유 기간

                    <br />&nbsp;&nbsp;&nbsp;① Polling은 정보주체로부터 개인정보를 수집할 때 동의 받은 개인정보 보유․이용기간 또는 법령에 따른 개인정보 보유․이용기간 내에서 개인정보를 처리․보유합니다.
                    <br />&nbsp;&nbsp;&nbsp;② 구체적인 개인정보 처리 및 보유 기간은 다음과 같습니다.
                    <br />- 고객 가입 및 관리 : 서비스 이용계약 또는 회원가입 해지시까지, 다만 채권․채무관계 잔존시에는 해당 채권․채무관계 정산시까지
                    </Typography>
                    <br /><br />
                    <Typography gutterBottom>
                    3. 정보주체와 법정대리인의 권리·의무 및 그 행사방법 이용자는 개인정보주체로써 다음과 같은 권리를 행사할 수 있습니다.
                    <br />
                    &nbsp;&nbsp;&nbsp;1. 개인정보 열람요구<br />
                    &nbsp;&nbsp;&nbsp;2. 오류 등이 있을 경우 정정 요구<br />
                    &nbsp;&nbsp;&nbsp;3. 삭제요구<br />
                    &nbsp;&nbsp;&nbsp;4. 처리정지 요구
                    </Typography>
                    <br /><br />
                    <Typography gutterBottom>
                    4. 처리하는 개인정보의 항목 작성<br />

                    &nbsp;&nbsp;&nbsp;① Polling은 다음의 개인정보 항목을 처리하고 있습니다. <br />

                    &lt;개인정보 처리업무&gt;<br />
                    - 필수항목 : 이메일, 소셜 로그인 ID, 비밀번호, 휴대전화번호, 이름, 성별, 생년월일, 서비스 이용 기록, 결제기록
                    </Typography>
                    <br /><br />
                    <Typography gutterBottom>
                    5. 개인정보의 파기<br />
                    Polling은 원칙적으로 개인정보 처리목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다. <br />파기의 절차, 기한 및 방법은 다음과 같습니다.
                    <br /><br />
                    &nbsp;&nbsp;&nbsp;- 파기절차<br />
                    이용자가 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져 &#40;종이의 경우 별도의 서류&#41; 내부 방침 및 기타 관련 법령에 따라 일정기간 저장된 후 혹은 즉시 파기됩니다. 이 때, DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다.
                    <br /><br />
                    &nbsp;&nbsp;&nbsp;- 파기기한<br />
                    이용자의 개인정보는 개인정보의 보유기간이 경과된 경우에는 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.
                    </Typography>
                    <br /><br />

                    <Typography gutterBottom>
                    6. 개인정보 제3자 제공에 관한 동의 내용 및 방법<br />


                    Polling은 회원의 개인정보를 수집이용목적에 명시한 범위 내에서 사용하며, 회원의 동의 없이 개인정보 수집, 이용목적 범위를 초과하여 이용하거나 회원의 개인정보를 제공하지 않습니다.<br />

                    다만 다음과 같은 상황은 예외로 합니다.<br />

                    &nbsp;&nbsp;&nbsp;- 대회 주관사가 투표내역 및 참여정보를 요구할 때 회원의 투표내역 및 개인정보 &#40;이름, 휴대폰 번호, 이메일&#41;<br />

                    &nbsp;&nbsp;&nbsp;- 대회 후보자가 주관사의 동의를 얻어 투표내역을 요구할 때 투표내역
                    </Typography>
                    <br /><br />

                    <Typography gutterBottom>
                    7. 개인정보의 위탁처리<br />


                    Polling은 편리하고 더 나은 서비스를 제공하기 위해 엄무 중 일부를 위탁하고 있습니다.<br />

                    &nbsp;&nbsp;&nbsp;&#40;주&#41;다날 - 신용카드 결제처리, 위탁 계약 만료 시까지 보유<br />

                    &nbsp;&nbsp;&nbsp;NICE신용평가&#40;주&#41; - 휴대폰 본인인증, 위탁 계약 만료 시까지 보유
                    </Typography>
                    <br /><br />


                    <Typography gutterBottom>
                    8. 개인정보 자동 수집 장치의 설치•운영 및 거부에 관한 사항<br />

                    &nbsp;&nbsp;① Polling은 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠키&#40;cookie&#41;’를 사용합니다.
                    <br />&nbsp;&nbsp;② 쿠키는 웹사이트를 운영하는데 이용되는 서버&#40;http&#41;가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.
                    <br />&nbsp;&nbsp;&nbsp;가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.
                    <br />&nbsp;&nbsp;&nbsp;나. 쿠키의 설치•운영 및 거부 : 웹브라우저 상단의 도구 &gt; 인터넷 옵션 &gt; 개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부 할 수 있습니다.
                    <br />&nbsp;&nbsp;&nbsp;다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.
                    </Typography>
                    <br /><br />
                    <Typography gutterBottom>
                    9. 개인정보 보호책임자 작성<br />

                    &nbsp;&nbsp;① Polling은 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.
                    <br />
                    &nbsp;&nbsp;&nbsp;▶ 개인정보 보호책임자<br />
                    &nbsp;&nbsp;&nbsp;성명 : 김혜란<br />
                    &nbsp;&nbsp;&nbsp;직급 : CPO<br />
                    &nbsp;&nbsp;&nbsp;연락처 : ssafy@ssafy,<br />
                    &nbsp;&nbsp;&nbsp;※ 개인정보 보호 담당부서로 연결됩니다.<br /><br />

                    &nbsp;&nbsp;&nbsp;▶ 개인정보 보호 담당부서<br />
                    &nbsp;&nbsp;&nbsp;부서명 : 고객지원<br />
                    &nbsp;&nbsp;&nbsp;연락처 : ssafy@ssafy,<br /><br />

                    &nbsp;&nbsp;② 정보주체께서는 Polling의 서비스&#40;또는 사업&#41;을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. <br />Polling은 정보주체의 문의에 대해 지체 없이 답변 및 처리해드릴 것입니다.
                    </Typography>
                    <br /><br />
                    <Typography gutterBottom>
                    10. 개인정보 처리방침 변경<br />

                    이 개인정보처리방침은 시행일로부터 적용되며, 법령 및 방침에 따른 변경내용의 추가, 삭제 및 정정이 있는 경우에는 변경사항의 시행 7일 전부터 공지사항을 통하여 고지할 것입니다.
                    </Typography>
                    <br /><br />
                    <Typography gutterBottom>
                    11. 개인정보의 안전성 확보 조치<br /> Polling은 개인정보보호법 제29조에 따라 다음과 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.
                    <br /><br />
                    &nbsp;&nbsp;&nbsp;1. 내부관리계획의 수립 및 시행<br />
                    개인정보의 안전한 처리를 위하여 내부관리계획을 수립하고 시행하고 있습니다.<br />
                    <br />
                    &nbsp;&nbsp;&nbsp;2. 개인정보의 암호화<br />
                    이용자의 개인정보는 비밀번호는 암호화 되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화 하거나 파일 잠금 기능을 사용하는 등의 별도 보안기능을 사용하고 있습니다.
                    <br /><br />
                    &nbsp;&nbsp;&nbsp;3. 접속기록의 보관 및 위변조 방지<br />
                    개인정보처리시스템에 접속한 기록을 최소 6개월 이상 보관, 관리하고 있으며, 접속 기록이 위변조 및 도난, 분실되지 않도록 보안기능 사용하고 있습니다.
                    <br /><br />
                    &nbsp;&nbsp;&nbsp;4. 개인정보에 대한 접근 제한<br />
                    개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여,변경,말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.
                    </Typography>
        </DialogContent>
      </Dialog>
        </div>
    );
}
// export default PrivateInfo;