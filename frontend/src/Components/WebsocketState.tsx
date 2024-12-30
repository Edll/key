interface Props {
    error: string | null,
    activateClient: () => void
}


export const WebsocketState = ({error, activateClient}: Props) => {
    return <div>
        <h1>Server Offline</h1>
        <button onClick={() => activateClient()}>Try Reconnect</button>
        {error && <p className="key-error">
            {error}
        </p>
        }
    </div>
}