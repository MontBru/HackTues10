import Box from '@mui/material/Box';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import { FixedSizeList } from 'react-window';

function renderRow(list) {
    return function RenderRow(props) {
        const { index, style } = props;
        const item = list[index];

        return (
            <ListItem style={style} key={index} component="div" disablePadding>
                <ListItemButton>
                    <ListItemText primary={item} sx={{ color: 'rgba(200, 200, 200, 255)', display: "flex",  justifyContent: "center"}}/>
                </ListItemButton>
            </ListItem>
        );
    };
}

export default function ItemsList({list}) {
    const Row = renderRow(list);

    return (
        <Box
            sx={{ width: '100%', height: 300, maxWidth: 320, bgcolor: 'background.paper', borderRadius: 2 }}
        >
            <FixedSizeList
                height={300}
                width={320}
                itemSize={46}
                itemCount={list.length}
                overscanCount={5}
            >
                {Row}
            </FixedSizeList>
        </Box>
    );
}
