/**
 * @jest-environment jsdom
 */

import { render, screen } from '@testing-library/react';
import React from 'react';
import App from './App';


var renderResult;

beforeEach(() => {
  renderResult = render(<App />);
});


test('That Loan Amount ID Exists on Page', () => {
  const linkElement = screen.getByLabelText(/Loan Amount/i);
  expect(linkElement.id).toBe("loanAmount");
});

test('That Loan Period ID Exists on Page', () => {
  const linkElement = screen.getByLabelText(/Loan Period/i);
  expect(linkElement.id).toBe("loanPeriod");
});


test('That Interest Rate ID Exists on Page', () => {
  const linkElement = screen.getByLabelText(/Interest Rate/i);
  expect(linkElement.id).toBe("interestRate");
});

test('That Select Element ID Exists on Page', () => {
  const selectElement = renderResult.container.querySelector('#customerSelect');
  expect(selectElement.id).toBe("customerSelect");
});

test('That Form Submission ID Exists on Page', () => {
  const buttonElement = screen.getByRole('button', {id: /calculateLoanPayments/i })
  expect(buttonElement.id).toBe("calculateLoanPayments");
});

