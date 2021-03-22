import { Switch, Route, Redirect } from 'react-router';
import { LoginScreen } from './ui/screen/LoginScreen/LoginScreen'
import './App.css';
import { Header } from './ui/component/Header/Header.component';
import { RegisterScreen } from './ui/screen/RegisterScreen/RegisterScreen'
import { HomeScreen } from './ui/screen/HomeScreen/HomeScreen';
import { TaskScreen } from './ui/screen/TaskScreen/TaskScreen';
import { CreateTaskScreen } from './ui/screen/CreateTaskScreen/CreateTaskCreen';
import { ErroScreen } from './ui/screen/ErrorScreen/ErroScreen';
import { DashboardScreen } from './ui/screen/DashboardScreen/DashboardScreen';

function PrivateRoute({ path, children }) {
  const user = JSON.parse(localStorage.getItem('user'))

  if (!user) {
    return <Redirect to="/login" />
  }

  return (
    <Route path={path} exact>
      {children}
    </Route>
  )
}

function App() {
  return (
    <div className="container">
      <Header />
      <Switch>
        <Route path="/" exact>
          <HomeScreen />
        </Route>
        <Route path="/login" exact>
          <LoginScreen />
        </Route>
        <Route path="/register" exact>
          <RegisterScreen />
        </Route>
        <PrivateRoute path="/tasks">
          <TaskScreen />
        </PrivateRoute>
        <PrivateRoute path="/task/create">
          <CreateTaskScreen />
        </PrivateRoute>
        <PrivateRoute path="/dashboard">
          <DashboardScreen />
        </PrivateRoute>
        <Route path="/*">
          <ErroScreen />
        </Route>
      </Switch>
    </div>
  );
}

export default App;
