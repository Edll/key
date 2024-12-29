import React from 'react';
import '@testing-library/jest-dom';
import {render, screen} from '@testing-library/react';
import App from '../Components/App.tsx';
import {WordMapWrapper} from "../Components/WordMapWrapper.tsx";


test('renders Server Offline', () => {
    render(<App/>);
    const linkElement = screen.getByText(/Server Offline/i);
    expect(linkElement).toBeInTheDocument();
});

test('renders loading...', () => {
    render(<WordMapWrapper data={null}/>);
    const linkElement = screen.getByText(/Loading.../i);
    expect(linkElement).toBeInTheDocument();
});