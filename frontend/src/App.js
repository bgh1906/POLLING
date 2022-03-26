import { BrowserRouter, Routes, Route } from "react-router-dom";
import Admin from "./routes/Admin";
import Home from "./routes/Home";
import Mypage from "./routes/Mypage";
import CreatePoll from "./routes/CreatePoll";
import PollList from "./routes/PollList";
import History from "./routes/History";
import Hall from "./routes/Hall";
import Poll from "./routes/Poll";
import Candidate from "./routes/Candidate";
import Notice from "./routes/Notice";
import Management from "./routes/Management";
import User from "./routes/User";
import Login2 from "./routes/Login2";
import Join2 from "./routes/Join2";
import Companylogin2 from "./routes/Companylogin2";
import WhyPolling from "./routes/WhyPolling";
import Candidate2 from "./routes/Candidate2";
import UpdatePoll from "./routes/UpdatePoll";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/join" element={<Join2 />}></Route>
          <Route path="/login" element={<Login2 />}></Route>
          <Route path="/Companylogin" element={<Companylogin2 />}></Route>
          <Route path="/admin" element={<Admin />}></Route>
          <Route path="/management" element={<Management />}></Route>
          <Route path="/user" element={<User />}></Route>
          <Route path="/mypage" element={<Mypage />}></Route>
          <Route path="/createpoll" element={<CreatePoll />}></Route>
          <Route path="/polllist" element={<PollList />}></Route>
          <Route path="/history" element={<History />}></Route>
          <Route path="/hall" element={<Hall />}></Route>
          <Route path="/notice" element={<Notice />}></Route>
          <Route path="/poll/:pollnum" element={<Poll />}></Route>
          {/* <Route path="/poll/:pollnum/:id/1" element={<Candidate />}></Route> */}
          {/* <Route path="/poll/:pollnum/:id/2" element={<Candidate2 />}></Route> */}
          <Route path="/nominee" element={<Candidate />}></Route>
          <Route path="/nominee2" element={<Candidate2 />}></Route>
          <Route path="/whypolling" element={<WhyPolling />}></Route>
          <Route path="/poll/update/:pollnum" element={<UpdatePoll />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
