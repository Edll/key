import {ISortBy} from "../interfaces/ISortBy.ts";
import {useMemo} from "react";

interface Props {
    field: "Count" | "Word"
    sortBy: ISortBy | null
    onSort: (sortBy: ISortBy) => void
}

export const TableHeader = ({field, sortBy, onSort}: Props) => {

    const arrow = useMemo(() => {
        if (sortBy?.field === field && sortBy?.direction === 'asc') {
            return 8593
        } else if (sortBy?.field === field && sortBy?.direction === 'desc') {
            return 8595
        } else {
            return 0
        }
    }, [sortBy, field]);


    const _onSort = () => {
        if (sortBy?.field === field) {
            onSort({field, direction: sortBy.direction === 'asc' ? 'desc' : 'asc'})
        } else {
            onSort({field, direction: 'asc'})
        }
    }

    return (
        <th>
            <button onClick={() => _onSort()}>{field} {String.fromCharCode(arrow)}</button>
        </th>
    )
}