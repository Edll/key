import React from 'react';
import '@testing-library/jest-dom';
import {render, screen} from '@testing-library/react';
import {TableHeader} from "../Components/TableHeader.tsx";


test('renders Word', () => {
    render(<TableHeader field={'Word'} sortBy={null} onSort={() => {
    }}/>);
    const linkElement = screen.getByText(/Word/i);
    expect(linkElement).toBeInTheDocument();
});

test('renders Count Sorted DESC', () => {
    render(<TableHeader field={'Count'} sortBy={{field: 'Count', direction: 'desc'}} onSort={() => {
    }}/>);
    const linkElement = screen.getByText(/Count ↓/i);
    expect(linkElement).toBeInTheDocument();
});

test('renders Count Sorted ASC', () => {
    render(<TableHeader field={'Count'} sortBy={{field: 'Count', direction: 'asc'}} onSort={() => {
    }}/>);
    const linkElement = screen.getByText(/Count ↑/i);
    expect(linkElement).toBeInTheDocument();
});
