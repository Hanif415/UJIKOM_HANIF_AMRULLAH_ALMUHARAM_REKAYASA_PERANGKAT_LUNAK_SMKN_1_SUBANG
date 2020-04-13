<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\Level;

class Petugas extends Model
{
    protected $guarded = ['id_petugas'];
    protected $primaryKey = 'id_petugas';

    public function level()
    {
        return $this->belongsTo(Level::class);
    }
}
