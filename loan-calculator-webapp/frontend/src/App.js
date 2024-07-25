import CalculatePayments from "./components/Modal";
import React from 'react';
import axios from 'axios';

function App() {
  const [customers, setcustomers] = React.useState('');

  React.useEffect(() => {
    const url =
        '/api/customers/';
    axios.get(url).then((response) => {
        setcustomers(response.data);
    });
}, []);

  return (
    
    <div id="AppDiv">
       <hr class="rounded"/>
             <div>
              <h3>Amortization Loan Details Form: </h3>
              <h5>Please Fill Out The Loan Details Below:</h5>
            </div>
            <hr class="rounded"/>
      <label>
      * Please Select the Customer:
       <select      id="customerSelect"
                    onChange={(e) => {
                        customers?.find(
                            (x) => x.id === e.target.value
                        );
                        document.getElementById("customer").value = e.target.value;
                    }}
                required>
                    <option value="">Choose an option</option>
                    {customers
                        ? customers.map((thecustomer) => {
                              return (
                                  <option key={thecustomer.id} value={thecustomer.id}>
                                      {thecustomer.first_name}-{thecustomer.last_name}
                                  </option>
                              );
                          })
                        : null}
                </select>
                </label>
      <CalculatePayments/>
    </div>
  )
}

export default App;