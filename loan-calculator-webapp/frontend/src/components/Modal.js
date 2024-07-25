import React from 'react';
import axios from 'axios';

export default class CalculatePayments extends React.Component {

  
    state = {
    loanAmount: '',
    loanPeriod: '',
    interestRate: '',
    monthlyPayment: ''
    }


  handleLoanAmountChange = event => {
    this.setState({ loanAmount: event.target.value });
  }
  handleLoanPeriodChange = event => {
    this.setState({ loanPeriod: event.target.value });
  }
  handleInterestChange = event => {
    this.setState({ interestRate: event.target.value });
  }

  handleSubmit = event => {
    event.preventDefault();
    if(validateCustomerSelect()){
        var custNo = document.getElementById("customer").value
        axios.put(`/api/loanoffers/${custNo}/`, 
            {
                customer: custNo,
                loanAmount: this.state.loanAmount,
                loanPeriod: this.state.loanPeriod,
                interestRate: this.state.interestRate,
                monthlyPayment: CalculateAmortization(this.state.loanAmount, this.state.loanPeriod, this.state.interestRate)
              }
        )
        .then((response) => displayOutput(response));


        const displayOutput = (responseDisplay) => {
             document.getElementById("responseDisplay").innerHTML = `
             <hr class="rounded"/>
             <div>
              <h5>Loan Calculations: </h5>
            </div>
            <hr class="rounded"/>
            <div>
              <div>
                <pre>${JSON.stringify(responseDisplay.data, null, 2)}</pre>
              </div>
            </div>
        
            </div>;`
          };
    }      
  }


  render() {
    
    return (
        
        <div id="formSubmissionDiv">
            <form onSubmit={this.handleSubmit}>
            <input type="hidden" id="customer" name="customer"/>
            <label>
                * Loan Amount:
                <input type="number" id="loanAmount" name="loanAmount" onChange={this.handleLoanAmountChange}  min="0" max="1000000000.00" step=".01" required/>
            </label>
            <label>
                * Loan Period (Months):
                <input type="number" id="loanPeriod" name="loanPeriod" onChange={this.handleLoanPeriodChange}  min="0" required/>
            </label>
            <label>
                * Interest Rate (%):
                <input type="number" id="interestRate" name="interestRate" onChange={this.handleInterestChange}  pattern="^[0-9]{0,3}\.[0-9]{1,2}$" min="0" max="100.00" step=".01" required />
            </label>
            <br/>
            <button id="calculateLoanPayments" type="submit" class="btn btn-primary">Calculate Amortized Payments</button>
            </form> 
            <span id="responseDisplay"/>
        </div>
  
    )
  }
}

function validateCustomerSelect() {
    if (document.getElementById("customerSelect").value === ""){
        alert("A Customer must be Associated with Loan Offer!");
        return false;
    }
    return true;
}

function CalculateAmortization(loanAmount, loanPeriod, interestRate) {
    var r = Math.pow(1 + ((interestRate/100) / 360), (360 / 12)) - 1;
    return (loanAmount * ((r * Math.pow(1 + r, loanPeriod)) / (Math.pow(1 + r, loanPeriod) - 1))).toFixed(2);     
  
  }
