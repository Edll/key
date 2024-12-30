import React from 'react';
import '@testing-library/jest-dom';
import {render, screen} from '@testing-library/react';
import {WordMapWrapper} from "../Components/WordMapWrapper.tsx";
import {WebsocketState} from "../Components/WebsocketState.tsx";


test('renders Server Offline', () => {
    render(<WebsocketState error={null} activateClient={() => console.log('activateClient')}/>);
    const linkElement = screen.getByText(/Server Offline/i);
    expect(linkElement).toBeInTheDocument();
});

test('renders loading...', () => {
    render(<WordMapWrapper data={null}/>);
    const linkElement = screen.getByText(/Loading.../i);
    expect(linkElement).toBeInTheDocument();
});