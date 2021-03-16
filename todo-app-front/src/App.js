import { Switch, Route, Redirect } from 'react-router';
import { LoginScreen } from './ui/screen/LoginScreen/LoginScreen'
import { HelloScreen } from './ui/screen/HelloScreen/HelloScreen'
import './App.css';

function PrivateRoute({path, children}) {
  const user = JSON.parse(localStorage.getItem('user'))

  if(!user) {
    return <Redirect to="/login"/>
  }

  return (
    <Route path={path} exact>
      {children}
    </Route>
  )
}

function App() {
  return (
    <Switch>
      <Route path="/" exact>
        <h1>Home</h1>
      </Route>
      <Route path="/login" exact>
        <LoginScreen />
      </Route>
      <PrivateRoute path="/hello">
        <HelloScreen />
      </PrivateRoute>
      <Route path="/*">
        <h1>Not Found</h1>
      </Route>

    </Switch>
  );
}

export default App;
