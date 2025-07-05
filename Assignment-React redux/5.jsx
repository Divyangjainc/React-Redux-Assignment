// src/SearchableTable.js
import React, { useState } from 'react';

const initialData = [
  { id: 1, name: 'Rahul Sharma', age: 28, city: 'Mumbai' },
  { id: 2, name: 'Priya Singh', age: 34, city: 'Delhi' },
  { id: 3, name: 'Amit Kumar', age: 45, city: 'Bangalore' },
  { id: 4, name: 'Sneha Roy', age: 22, city: 'Kolkata' },
];

export default function SearchableTable() {
  const [searchTerm, setSearchTerm] = useState('');
  const filteredData = initialData.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    item.city.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div style={{ padding: '1rem' }}>
      <input
        type="text"
        placeholder="Search name or city..."
        value={searchTerm}
        onChange={e => setSearchTerm(e.target.value)}
        style={{ padding: '0.5rem', width: '100%', marginBottom: '1rem' }}
      />
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead style={{ background: '#eee' }}>
          <tr><th>ID</th><th>Name</th><th>Age</th><th>City</th></tr>
        </thead>
        <tbody>
          {filteredData.map(item => (
            <tr key={item.id}>
              <td>{item.id}</td><td>{item.name}</td>
              <td>{item.age}</td><td>{item.city}</td>
            </tr>
          ))}
          {filteredData.length === 0 && (
            <tr><td colSpan="4" style={{ textAlign: 'center' }}>No results</td></tr>
          )}
        </tbody>
      </table>
    </div>
  );
}
