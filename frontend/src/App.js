import { BrowserRouter, Routes, Route } from "react-router-dom";
import Admin from "./routes/Admin";
import Home from "./routes/Home";
import Join from "./routes/Join";
import Login from "./routes/Login";
import Mypage from "./routes/Mypage";
import CreatePoll from "./routes/CreatePoll";
import PollList from "./routes/PollList";
import History from "./routes/History";
import Hall from "./routes/History";
import Poll from "./routes/Poll";
import Nominee from "./routes/Nominee";
import Notice from "./routes/Notice";
import Management from "./routes/Management";
import User from "./routes/User";


function App() {
  return (
    <div>

    <BrowserRouter>
        <Routes>
          
          <Route path="/" element={<Home />}></Route>
          <Route path="/join" element={<Join />}></Route>
          <Route path="/login" element={<Login />}></Route>
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
          <Route path="/poll/:pollnum/:name" element={<Nominee />}></Route>

        </Routes>
    </BrowserRouter>
    
    </div>
  );
}

export default App;
